package task.gpt.generics.repository;

public interface Condition<T> {
    boolean test(T item);
}
