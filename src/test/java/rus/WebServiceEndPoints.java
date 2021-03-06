package rus;

public enum WebServiceEndPoints {
    STATUS                  (""),

    PET_ID                  ("v2/pet/"),
    FIND_PET_BY_STATUS      ("v2/pet/findByStatus?status="),

    GET_STORE_INVENTORY     ("v2/store/inventory"),
    GET_STORE_ORDER         ("v2/store/order"),

    GET_USER_LOGIN          ("v2/user/login?username=%s&password=%s"),
    USER                    ("v2/user"),
    GET_USER_LOGOUT         ("v2/user/logout"),
    USER_CREATE_ARRAY       ("v2/user/createWithArray"),
    USER_CREATE_LIST        ("v2/user/createWithList"),
    ;

    private final String url;

    WebServiceEndPoints(String url) {
        this.url = "https://petstore.swagger.io/" + url;
    }

    public String getUrl() {
        return url;
    }



}
