package org.example;

import javax.swing.*;  // Импортируем классы для работы с графическим интерфейсом Swing
import javax.swing.table.DefaultTableModel;  // Импортируем класс для работы с моделью таблицы
import java.awt.*;  // Импортируем классы для установки макета и графического интерфейса
import java.util.ArrayList;  // Импортируем класс для работы с динамическим массивом
import java.util.LinkedList;  // Импортируем класс для работы с двусвязным списком
import java.util.List;  // Импортируем интерфейс для работы со списками
import java.util.Random;  // Импортируем класс для генерации случайных чисел

public class Main {

    private static DefaultTableModel tableModel;  // Объявляем таблицу для хранения и отображения результатов тестирования

    public static void main(String[] args) {
        // Используем SwingUtilities для обеспечения того, что GUI создается в потоке событий
        SwingUtilities.invokeLater(() -> {
            // Создаем новое JFrame для отображения результатов тестов
            JFrame frame = new JFrame("Performance Test Results");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Завершение программы при закрытии окна
            frame.setLayout(new BorderLayout());  // Устанавливаем макет окна на BorderLayout

            // Создание таблицы с заголовками
            String[] columnNames = {"Method", "Size", "ArrayList_Time(ns)", "LinkedList_Time(ns)"};
            tableModel = new DefaultTableModel(columnNames, 0);  // Инициализируем модель таблицы с заголовками и без данных
            JTable table = new JTable(tableModel);  // Создаем JTable с использованием модели таблицы

            // Добавление таблицы в JScrollPane для поддержки прокрутки
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);  // Добавляем прокручиваемую таблицу в центр окна

            // Запуск тестов производительности
            testPerformance(1000);  // Передаем размер данных для тестирования (1000 элементов)

            // Настройки окна
            frame.setSize(800, 600);  // Устанавливаем размер окна
            frame.setVisible(true);  // Делаем окно видимым
        });
    }

    // Метод для тестирования производительности различных методов коллекций
    public static void testPerformance(int size) {
        // Создаем списки для тестирования
        List<Integer> arrayList = new ArrayList<>();  // Инициализируем ArrayList
        List<Integer> linkedList = new LinkedList<>();  // Инициализируем LinkedList
        Random random = new Random();  // Создаем генератор случайных чисел

        // Заполняем списки значениями от 0 до size-1
        for (int j = 0; j < size; j++) {
            arrayList.add(j);  // Добавляем значение в ArrayList
            linkedList.add(j);  // Добавляем значение в LinkedList
        }

        // Тестируем методы и добавляем результаты в таблицу
        addRowToTable("ADD", size, measureAddPerformance(new ArrayList<>(), size), measureAddPerformance(new LinkedList<>(), size));
        addRowToTable("GET", size, measureGetPerformance(arrayList, size), measureGetPerformance(linkedList, size));
        addRowToTable("Add_Middle", size, measureAddMiddlePerformance(arrayList, size, random), measureAddMiddlePerformance(linkedList, size, random));
        addRowToTable("REMOVE", size, measureRemovePerformance(arrayList), measureRemovePerformance(linkedList));
    }

    // Метод для измерения времени выполнения добавления элементов в список
    private static long measureAddPerformance(List<Integer> list, int size) {
        long startTime = System.nanoTime();  // Запоминаем текущее время в наносекундах
        for (int j = 0; j < size; j++) {
            list.add(j);  // Добавляем элемент j в список
        }
        return System.nanoTime() - startTime; // Возвращаем время выполнения в наносекундах
    }

    // Метод для измерения времени выполнения получения элементов из списка
    private static long measureGetPerformance(List<Integer> list, int size) {
        long startTime = System.nanoTime();  // Запоминаем текущее время в наносекундах
        for (int j = 0; j < size; j++) {
            list.get(j);  // Получаем элемент по индексу j
        }
        return System.nanoTime() - startTime; // Возвращаем время выполнения в наносекундах
    }
  
    // Метод для добавления строки с результатами в таблицу
    private static void addRowToTable(String method, int size, long timeArrayList, long timeLinkedList) {
        // Добавление новой строки с данными в модель таблицы
        tableModel.addRow(new Object[]{method, size, timeArrayList, timeLinkedList});
    }
}
