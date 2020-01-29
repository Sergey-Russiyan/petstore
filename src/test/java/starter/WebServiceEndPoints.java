package starter;

public enum WebServiceEndPoints {
    STATUS                  (""),
    PET_ID                  ("v2/pet/"),
    GET_STORE_INVENTORY     ("v2/store/inventory"),
    GET_USER_LOGIN          ("v2/user/login?username=%s&password=%s"),
    USER_WITH_NAME          ("v2/user/"),
    GET_USER_LOGOUT         ("v2/user/logout"),
    ;

    private final String url;

    WebServiceEndPoints(String url) {
        this.url = "https://petstore.swagger.io/" + url;
    }

    public String getUrl() {
        return url;
    }
}
