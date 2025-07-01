package seang.spring.testingmvc.exceptionHandler;

public record HandleResponse<T>(
        String status,
        String timestamp,
        String message,
        T data
) {
}
