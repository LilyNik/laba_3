package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** * Тестирование поведения {@link ArrayList}. */
class ArrayListTest {

    // Список целых чисел для тестирования
    private List<Integer> array_list;

    /** * Инициализация списка перед каждым тестовым методом. */
    @BeforeEach
    void set_up() {
        array_list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            array_list.add(i);
        }
    }

    /** * Проверка размера списка после добавления 100 элементов. */
    @Test
    void when_added_100_elements_then_size_must_be_100() {
        assertEquals(100, array_list.size());
    }

    /** * Проверка уменьшения размера списка при удалении элемента по индексу. */
    @Test
    void when_element_removed_by_index_then_size_must_be_decreased() {
        assertTrue(array_list.remove(5) != null);
        assertEquals(99, array_list.size());
    }

    /** * Проверка уменьшения размера списка при удалении произвольного элемента. */
    @Test
    void when_element_removed_then_size_must_be_decreased() {
        array_list.add(102);
        assertEquals(101, array_list.size());
        assertTrue(array_list.remove(Integer.valueOf(102)));
        assertEquals(100, array_list.size());
    }

    /** * Проверка очистки списка. */
    @Test
    void when_list_cleared_then_size_must_be_0() {
        array_list.clear();
        assertEquals(0, array_list.size());
    }

    /** * Проверка исключения при попытке получить элемент по несуществующему индексу. */
    @Test
    void when_index_out_of_bounds_then_thrown_exception() {
        assertThrows(IndexOutOfBoundsException.class, () -> array_list.get(100));
    }

    /** * Проверка корректности метода get(). */
    @Test
    void method_get_returned_right_value() {
        int value = array_list.get(0);
        assertEquals(0, value);
    }

    /** * Вставка нового элемента в середину списка. */
    @Test
    void insert_into_middle() {
        array_list.add(50, 66);
        assertEquals(66, array_list.get(50).intValue());
    }

    /** * Вставка нового элемента в начало списка. */
    @Test
    void insert_into_first_position() {
        array_list.add(0, 0);
        assertEquals(0, array_list.get(0).intValue());
    }

    /** * Вставка нового элемента в конец списка. */
    @Test
    void insert_into_last_position() {
        array_list.add(100, 100);
        assertEquals(100, array_list.get(100).intValue());
    }
}
