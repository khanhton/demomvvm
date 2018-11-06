package mvvm.bsv.vn.basemvvm.notifimodelview;

public interface BaseNotifiModelview {
    void loadAPIFail(Throwable throwable);

    void loadAPIError(String message);

    void hideLoading();

    void showLoading();
}
