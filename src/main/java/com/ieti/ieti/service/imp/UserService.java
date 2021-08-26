package com.ieti.ieti.service.imp;


import com.ieti.ieti.data.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements com.ieti.ieti.service.UserService {
    private HashMap<String,User> userPersistence =new HashMap<String,User>();

    /**
     * Constructor que añade dos usuarios ejemplo al HashMap userPersistence
     */
    public UserService(){
        User testUser1=new User(UUID.randomUUID().toString(), "Luigi", "luigi@64", "Green");
        User testUser2=new User(UUID.randomUUID().toString(), "Mario", "mario@64", "Red");
        userPersistence.put(testUser1.getId(), testUser1);
        userPersistence.put(testUser2.getId(), testUser2);

    }


    /**
     * Crea un usuario nuevo que llega por medio del parametro user y lo introduce al HashMap, la llave es el id de user
     * @param user
     * @return
     */

    @Override
    public User create(User user) {
        User userCreated=new User(user.getId(),user.getName(),user.getEmail(),user.getLastName());
        userPersistence.put(userCreated.getId(),userCreated);
        return userCreated;
    }

    /**
     * Busca en el HashMap userPersistence el identificador que llega como parametro
     * Retorna el objeto User del usuario si lo encuentra
     * Retorna null si no encuentra el id del usuario dentro de userPersistence
     * @param id
     * @return
     */
    @Override
    public User findById(String id) {
        User foundUser=null;
        foundUser= userPersistence.get(id);
        return foundUser;
    }

    /**
     * Hace un ArrayList con todos los valores del HashMap userPersistence
     * @return
     */
    @Override
    public List<User> all() {
        ArrayList<User> usersList=new ArrayList<User>(userPersistence.values());
        return usersList;

    }

    /**
     * Busca el identificador id de un usuario, si lo encuentra elimina el usuario, si no lo encuentra no realiza ninguna accion
     * @param id
     */
    @Override
    public void deleteById(String id) {
        userPersistence.remove(id);

    }

    /**
     * Actualiza un usuario ya existente con uno nuevo, el usuario actualizado conserva su id original
     * Retorna el usuario actualizado si logra encontra con en userPersistence el id userId
     * Retorna null en caso de que no se encuentre el usuario que se va a actualizar
     * @param user
     * @param userId
     * @return
     */
    @Override
    public User update(User user, String userId) {
        if(userPersistence.get(userId)==null){
            return null;
        }
        else{
            userPersistence.put(user.getId(),user);
            return user;
        }

    }
}
