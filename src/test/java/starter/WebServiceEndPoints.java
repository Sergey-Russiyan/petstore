package starter;

public enum WebServiceEndPoints {
    STATUS                  (""),
    GET_PET                 ("v2/pet/"),
    GET_STORE_INVENTORY     ("v2/store/inventory"),
    GET_USER_LOGIN          ("v2/user/login?username=%s&password=%s"),
    ;

    private final String url;

    WebServiceEndPoints(String url) {
        this.url = "https://petstore.swagger.io/" + url;
    }

    public String getUrl() {
        return url;
    }
}
