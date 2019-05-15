package ru.bellintegrator.practice.exception;

/**
 * Исключение выбрасывается в случае отсутствия искомой записи в базе данных
 */
public class RecordNotFoundException extends RuntimeException {

    /**
     * Конструктор
     *
     * @param message сообщение, описывающее исключительную ситуацию
     */
    public RecordNotFoundException(String message) {
        super(message);
    }
}
