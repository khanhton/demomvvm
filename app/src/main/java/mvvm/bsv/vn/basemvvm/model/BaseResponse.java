package mvvm.bsv.vn.basemvvm.model;

/**
 * Created by Khanh Ton on 2019-05-30.
 */
public class BaseResponse {
    int status_code;
    String message;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
