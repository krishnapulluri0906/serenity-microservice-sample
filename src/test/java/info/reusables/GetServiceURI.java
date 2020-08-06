package info.reusables;

public enum GetServiceURI {

    EMPLOYEES("http://dummy.restapiexample.com/api/v1/employees"),
    SPECIFIC_EMPLOYEES("http://dummy.restapiexample.com/api/v1/employee/EMP_ID"),
    CREATE("http://dummy.restapiexample.com/api/v1/create");


    public String uri;

    GetServiceURI(String uri) {
        this.uri = uri;
    }

}
