package user.controller;


import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import user.service.UserService;

//在Spring中@RestController的作用等同于@Controller + @ResponseBody。
@RestController
public class UserController {

//    @RequestMapping("/user/{pid}")
    @Autowired
    private UserService userService;

    @RequestMapping("/user/select")
    public User select(@RequestParam("uid") Integer uid){

        User user=userService.select(uid);

        return user;
    }

    @RequestMapping("/user/update")
    public void update(Integer uid,Integer deposit){
        User user=userService.select(uid);
        int dps=user.getDeposit();
        deposit=dps-deposit;
        userService.update(uid,deposit);

    }

}
