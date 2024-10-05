package tech.buildrun.picpay.client.dto;

public class AuthorizationResponse {

    private String status;
    private Data data;

    // Getters e Setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    // Classe interna para mapear o campo "data"
    public static class Data {
        private boolean authorization;

        public boolean isAuthorization() {
            return authorization;
        }

        public void setAuthorization(boolean authorization) {
            this.authorization = authorization;
        }
    }
}

