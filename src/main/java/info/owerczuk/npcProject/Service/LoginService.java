package info.owerczuk.npcProject.Service;

import info.owerczuk.npcProject.DAO.UserRepository;
import info.owerczuk.npcProject.Domain.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean checkLogin(String username, String password, UserRepository userRepository){

        User user = userRepository.findByUsername(username);
        if(user == null) {
            return false;
        }
        else if (!user.getPassword().equals(password)){
            return false;
        }
        return true;
    }

}
