package user.mapper;

import domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User select(Integer uid);

    void update(Integer uid, Integer deposit);
}
