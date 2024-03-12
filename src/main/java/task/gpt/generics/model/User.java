package task.gpt.generics.model;

public class User<T> {
    private T item;

    public User(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }


    @Override
    public String toString() {
        return "User{" +
               "item=" + item +
               '}';
    }
}
