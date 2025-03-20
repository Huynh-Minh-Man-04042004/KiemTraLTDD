package nathja.finalproject.kiemtraltdd;

public class LoginResponse {
    private String status;
    private Data data;
    private String message;
    private String errors;

    public String getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public String getErrors() {
        return errors;
    }

    // Class để ánh xạ "data"
    public static class Data {
        private String token;

        public String getToken() {
            return token;
        }
    }
}
