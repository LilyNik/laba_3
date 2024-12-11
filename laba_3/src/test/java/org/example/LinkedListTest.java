package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** * Тестирование поведения {@link LinkedList}. */
class LinkedListTest {
    // Список целых чисел для тестирования
    private List<Integer> linked_list;

    /** * Инициализация списка перед каждым тестовым методом. */
    @BeforeEach
    void set_up() {
        linked_list = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            linked_list.add(i);
        }
    }

    /** * Проверка размера списка после добавления 100 элементов. */
    @Test
    void when_added_100_elements_then_size_must_be_100() {
        assertEquals(100, linked_list.size());
    }

    /** * Проверка уменьшения размера списка при удалении элемента по индексу. */
    @Test
    void when_element_removed_by_index_then_size_must_be_decreased() {
        assertTrue(linked_list.remove(5) != null);
        assertEquals(99, linked_list.size());
    }

    /** * Проверка уменьшения размера списка при удалении произвольного элемента. */
    @Test
    void when_element_removed_then_size_must_be_decreased() {
        linked_list.add(102);
        assertEquals(101, linked_list.size());
        assertTrue(linked_list.remove(Integer.valueOf(102)));
        assertEquals(100, linked_list.size());
    }

    /** * Проверка очистки списка. */
    @Test
    void when_list_cleared_then_size_must_be_0() {
        linked_list.clear();
        assertEquals(0, linked_list.size());
    }

    /** * Проверка исключения при попытке получить элемент по несуществующему индексу. */
    @Test
    void when_index_out_of_bounds_then_thrown_exception() {
        assertThrows(IndexOutOfBoundsException.class, () -> linked_list.get(100));
    }

    /** * Проверка корректности метода get(). */
    @Test
    void method_get_returned_right_value() {
        int value = linked_list.get(0);
        assertEquals(0, value);
    }

    /** * Вставка нового элемента в середину списка. */
    @Test
    void insert_into_middle() {
        linked_list.add(50, 66);
        assertEquals(66, linked_list.get(50).intValue());
    }

    /** * Вставка нового элемента в начало списка. */
    @Test
    void insert_into_first_position() {
        linked_list.add(0, 0);
        assertEquals(0, linked_list.get(0).intValue());
    }

    /** * Вставка нового элемента в конец списка. */
    @Test
    void insert_into_last_position() {
        linked_list.add(100, 100);
        assertEquals(100, linked_list.get(100).intValue());
    }
}
