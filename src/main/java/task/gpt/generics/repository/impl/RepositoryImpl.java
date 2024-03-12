package task.gpt.generics.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import task.gpt.generics.repository.Condition;
import task.gpt.generics.repository.Repository;

public class RepositoryImpl<T> implements Repository<T> {
    private final HashMap<Integer, T> memoryMap;

    public RepositoryImpl() {
        this.memoryMap = new HashMap<>();
    }

    @Override
    public void add(T entity) {
        memoryMap.put(memoryMap.size(), entity);
    }

    @Override
    public T get(int id) {
        return memoryMap.get(id);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(memoryMap.values());
    }

    @Override
    public void update(int id, T item) {
        memoryMap.computeIfPresent(id, (key, oldValue) -> item);
    }

    @Override
    public void delete(int id) {
        memoryMap.remove(id);
    }

    @Override
    public List<T> findByCondition(Condition<T> condition) {
        return memoryMap.values()
            .stream()
            .filter(condition::test)
            .collect(Collectors.toList());
    }
}
