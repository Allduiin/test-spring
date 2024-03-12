package task.gpt.generics.repository;

import java.util.List;

public interface Repository<T> {
    void add(T entity);

    T get(int id);

    List<T> getAll();

    void update(int id, T item);

    void delete(int id);

    List<T> findByCondition(Condition<T> condition);
}
