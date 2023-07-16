package user.service;

import domain.User;

public interface UserService{
    User select(Integer uid);

    void update(Integer uid, Integer deposit);
}
