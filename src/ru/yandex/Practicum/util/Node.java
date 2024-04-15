package ru.yandex.Practicum.util;

import java.util.Objects;

public class Node<E> {
    public Node<E> prev;
    public E e;
    public Node<E> next;

    public Node(Node<E> prev, E e, Node<E> next) {
        this.prev = prev;
        this.e = e;
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(next, node.next) && Objects.equals(e, node.e) && Objects.equals(prev, node.prev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(next, e, prev);
    }
}
