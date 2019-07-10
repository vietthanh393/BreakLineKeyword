package com.vietthanh.tikidisplaykeyword.common.api;

import java.io.IOException;

public class NetworkException extends IOException {
    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
