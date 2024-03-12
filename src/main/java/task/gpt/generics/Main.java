package task.gpt.generics;

import java.util.List;
import task.gpt.generics.model.User;
import task.gpt.generics.repository.Condition;
import task.gpt.generics.repository.Repository;
import task.gpt.generics.repository.impl.RepositoryImpl;

public class Main {
    public static void main(String[] args) {
        Repository<User<Integer>> repository = new RepositoryImpl<>();
        int adultAge = 20;
        int childAge = 10;

        repository.add(new User<>(adultAge));
        repository.add(new User<>(childAge));

        User<Integer> actualAdult = repository.get(0);
        assert actualAdult.getItem().equals(adultAge);

        int updatedAge = 30;
        repository.update(0, new User<>(updatedAge));
        assert repository.get(0).getItem() == updatedAge;

        Condition<User<Integer>> adultUsersCondition = user -> user.getItem() >= 18;
        List<User<Integer>> adultUsers = repository.findByCondition(adultUsersCondition);
        System.out.println(adultUsers);

        List<User<Integer>> allUsers = repository.getAll();
        assert allUsers.size() == 2;
        System.out.println(allUsers);

        repository.delete(1);
        allUsers = repository.getAll();
        assert allUsers.size() == 1;
        System.out.println(allUsers);
    }
}
