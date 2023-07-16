package user.service.impl;

import domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.mapper.UserMapper;
import user.service.UserService;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User select(Integer uid) {

        User user=userMapper.select(uid);

        return user;
    }

    @Override
    public void update(Integer uid, Integer deposit) {
        userMapper.update(uid,deposit);
    }
}
