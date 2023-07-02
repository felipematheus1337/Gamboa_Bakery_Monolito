package br.combakery.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private int status;
    private String message;
    private LocalDateTime timestamp;
    private String url;

    public ErrorResponse(int status, String message, LocalDateTime timestamp, String url) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.url = url;
    }

}
