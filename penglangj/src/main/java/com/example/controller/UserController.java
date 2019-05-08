package com.example.controller;

import com.example.utils.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(HtmlController.class);

    @Resource
    private UserService userService;

    @Autowired
    private RedisTemplate<String, Object> template;

    @RequestMapping("/getpl")
    public String toIndex(HttpServletRequest request, Model model, HashMap<String, Object> map){
        String userId = request.getParameter("id");
        User user = this.userService.getUserById(userId);
        map.put("user",user);
        return "index";
    }

    @RequestMapping("/forms.do")
    public String forms (User user, HashMap<String, Object> map,HttpServletRequest request, HttpSession session) throws Exception{
        List<User> userList = userService.findByEmail(user.getEmail());
        user.setPassword(MD5.getInstance().getMD5ofStr(user.getPassword()+userList.get(0).getId()));
        user = this.userService.findIdAndPassword(user);
        template.opsForValue().set(user.getId(), user);

        User u2 = (User) template.opsForValue().get(user.getId());
        System.out.println("=================biubiu=============="+u2.getEmail()+"++++"+u2.getId()+".................");
        if(null==user){
                throw new Exception("用户不存在");
        }
        session.setAttribute("user",user);
        map.put("user",user);
        return "success/success";
    }
    @RequestMapping(value = "/test.do" )
    public String test (User user, HashMap<String, Object> map,HttpServletRequest request, HttpSession session) throws MyException{
        logger.info("============user================"+session.getAttribute("user"));
        user = (User) session.getAttribute("user");
        map.put("user",user);
        return "success/test";
    }


    @RequestMapping("/register.do")
    public String register (User user, String rpassword) throws MyException{

        logger.info("===============user.getPassword()========================"+user.getPassword());
        logger.info("===============rpassword========================"+rpassword);
        if(!user.getPassword().equals(rpassword)){
            throw new MyException("两次密码不一致！请重新输入！");
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String dates= simpleDateFormat.format(date);
        String idqian = (Long.parseLong(userService.findId().substring(0,7))+1l)+"";
        String id = idqian+dates;
        user.setId(id);
        user.setPassword(MD5.getInstance().getMD5ofStr(user.getPassword()+id));
        int suId = userService.insertSelective(user);
        if(suId == 0){
            throw new MyException("注册失败！");
        }
        return "success/success";
    }

    @RequestMapping(value = "/checkEmail.do",method = RequestMethod.POST)
    @ResponseBody
    public String checkEmail (String email, HashMap<String, Object> map) throws MyException {
        logger.info("=====================email==============="+email);
        if(null==email){
            email="";
        }
        List<User> userList = userService.findByEmail(email);
        String msg=null;
        if (userList.size()>0){
            msg="该邮箱已被注册！";
        }
        return msg;
    }

    @RequestMapping("/all.do")
    public String all () throws MyException{
        return "success/findAll";
    }

    @RequestMapping(value = "/findAllUser.do",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> findAllUser () throws MyException{
        Map<String, Object> map=new HashMap<String, Object>();
        List<User> userList = userService.findAllUser();
        map.put("userList",userList);
        return map;
    }
}