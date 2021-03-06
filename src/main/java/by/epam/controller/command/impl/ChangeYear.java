package by.epam.controller.command.impl;

import by.epam.bean.Request;
import by.epam.bean.Response;
import by.epam.bean.book.BookRequest;
import by.epam.bean.book.BookResponse;
import by.epam.controller.command.Command;
import by.epam.controller.command.CommandStatus;
import by.epam.service.BookService;
import by.epam.service.exception.ServiceException;
import by.epam.service.factory.ServiceFactory;

public class ChangeYear implements Command {
    @Override
    public Response execute(Request request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService = serviceFactory.getBookService();

        BookResponse bookResponse = new BookResponse();
        BookRequest bookRequest = (BookRequest)request;

        try {
            bookService.changeYear(bookRequest.getId(), bookRequest.getYear());
            bookResponse.setSuccessMessage("Year of a book successfully changed");
            bookResponse.setStatus(CommandStatus.SUCCESS);
        } catch (ServiceException e) {
            bookResponse.setErrorMessage("Error during year changing procedure");
            bookResponse.setStatus(CommandStatus.ERROR);
        }
        return bookResponse;
    }
}
