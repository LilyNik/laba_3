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
  
    // Метод для добавления строки с результатами в таблицу
    private static void addRowToTable(String method, int size, long timeArrayList, long timeLinkedList) {
        // Добавление новой строки с данными в модель таблицы
        tableModel.addRow(new Object[]{method, size, timeArrayList, timeLinkedList});
    }
}
