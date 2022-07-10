package com.example.test01.controller;

import com.example.test01.controller.ex.*;
import com.example.test01.entity.User;
import com.example.test01.service.IUserService;
import com.example.test01.service.ex.InsertException;
import com.example.test01.service.ex.UsernameDuplicateException;
import com.example.test01.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Keifer
 * @creat 2022-01-27-11:38
 */
@RequestMapping("users")
@RestController
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<>(OK);
    }
    @RequestMapping("login")                                    //传递的session数据注入到HttpSession类型的对象中
    public JsonResult<User> login(String username, String password,HttpSession session){
        User data=userService.login(username,password);
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        return new JsonResult<User>(OK,data);
    }
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String newPassword,String oldPassword,HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }
    @RequestMapping("get_by_uid")       //路径、返回值、参数
    public JsonResult<User> getByUid(HttpSession session){
        User data= userService.getByUid(getuidFromSession(session));
        return new JsonResult<User>(OK,data);
    }
    //user有四部分数据：username，phone，email，gender 要检查属性名是否与前端对应框的字段id是否一样，
    // 同名字段才会自动地注入到user对象当中
    @RequestMapping("change_info")
    public JsonResult<Void>changeInfo(User user,HttpSession session){
        userService.changeInfo(getuidFromSession(session),getUsernameFromSession(session),user);
        return new JsonResult<>(OK);
    }
    /**
     * API  MutipartFile接口是SpringMVC提供的一个接口，这个接口为我们包装类获取文件类型的数据(任何类型的file都可以接收)，
     * SpringBoot又整合了SpringMVC，只需要在请求处理方法的参数列表上声明一个参数类型为MutipartFile类型的参数，
     * 然后SpringBoot会自动将文件当中的数据赋值给这个参数
     * @RequestParam 表示请求中的参数，将请求中的参数注入到请求方法的某个参数上，如果名称不一致使用此注解进行强行标记和映射
     * @param session
     * @param file
     * @return
     */
    /*设置上传文件的最大值 10M 上传文件的单位一般是B 常量一般都是大写*/
    public static final int AVATAR_MAX_SIZE=10*1024*1024;
    /*限制上传文件的类型 以后可能要扩充所以用list*/
    public static final List<String> AVATAR_TYPE=
                                            new ArrayList<>();
    /*用static给集合作初始化*/
    static{
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }
    //再次上传头像的话要显示原来的头像所以要将原来的头像的路径返回,所以返回值为string
    @RequestMapping("change_avatar")
    public JsonResult<String>change_avatar(HttpSession session, @RequestParam("file") MultipartFile file){
        //判断文件是否为空
        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        //判断文件的大小是否超出限制
        if(file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件超出限制");
        }
        //判断文件的类型是否是我们规定的后缀类型
        String contentType=file.getContentType();
        //contains方法是否包含其中某个元素，如果集合包含某个元素则返回true
        if(!AVATAR_TYPE.contains(contentType)){
            throw new FileTypeException("文件不支持");
        }
        //上传文件 项目路径结构/upload/文件.png 提供了一个API getServletContext去找到上下文的getRealPath绝对的路径
        // 返回值是整个文件将来要存放的父目录结构 就是获取upload存放的绝对路径，
        // 第一次肯定是不存在的，所以要做判断，然后创建当前目录下的upload文件
        String parent=
        session.getServletContext().getRealPath("upload");
        //文件操作IO流 File对象指向这个路径，File是否存在,动态填充了一个目标的路径 parent文件路径
        File dir=new File(parent);
        if(!dir.exists()){  //检测目录是否存在
            dir.mkdirs();  //创建当前的目录
        }
        //获取到这个文件的名称，通过uuid工具来生成新的字符串作为文件名
        //返回的文件名 例如：avatar01.png
        //获取文件的后缀
        String originalFilename=file.getOriginalFilename();
        System.out.println("OriginalFilename="+originalFilename);
                         //找到最后小数点出现的位置
        int index= originalFilename.lastIndexOf(".");
                        //取index位置之后的内容 即取出后缀
        String suffix=originalFilename.substring(index);
        //通过UUID生成的文件名
        String filename= UUID.randomUUID().toString().toUpperCase()+suffix;
        //File提供了一个重载的方法 父目录结构 在这个目录结构下存放一个filename名的文件
        File dest=new File(dir, filename); //是一个空文件
        //参数file的数据写入到这个空文件中 MultipartFile提供了一个数据写入到带名字的空文件中的API即transferTo
        try {
            file.transferTo(dest);
        }catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }
        String username=getUsernameFromSession(session);
        Integer uid=getuidFromSession(session);
        //返回头像的相对路径 即默认项目路径下的 例如/upload/test.png
        String avatar="/upload/"+filename;
        userService.changeAvatar(uid,avatar,username);
        //返回用户头像的路径给前端界面，将来用于头像展示使用
        return new JsonResult<>(OK,avatar);
    }
}
