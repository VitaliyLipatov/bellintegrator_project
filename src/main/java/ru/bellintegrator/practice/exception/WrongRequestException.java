package ru.bellintegrator.practice.exception;

/**
 * Исключение выбрасывается при отсутствии обязательных полей в запросе, либо некорректном значении какого-либо из полей
 */
public class WrongRequestException extends RuntimeException {

    /**
     * Конструктор
     *
     * @param message сообщение, описывающее возникшую исключительную ситуацию
     */
    public WrongRequestException(String message) {
        super(message);
    }
}
