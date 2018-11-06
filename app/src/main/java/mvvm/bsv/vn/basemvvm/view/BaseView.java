package mvvm.bsv.vn.basemvvm.view;

public interface BaseView {
    void hideLoading();

    void showLoading();

    void loadAPIFail(Throwable throwable);

    void loadAPIError(String message);
}
