package com.brianestrada.boilerplate.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.brianestrada.boilerplate.App;
import com.brianestrada.boilerplate.injection.components.AppComponent;
import com.brianestrada.boilerplate.loader.PresenterFactory;
import com.brianestrada.boilerplate.loader.PresenterLoader;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseActivity<P extends BasePresenter<V>, V> extends AppCompatActivity implements LoaderManager.LoaderCallbacks<P> {
    /**
     * Common counter for views (fragments and activities) that is used to generate loader ids
     */
    static final AtomicInteger sViewCounter = new AtomicInteger(0);

    private final static String BUNDLE_KEY_FIRST_START = "BUNDLE_KEY_FIRST_START";
    private final static String BUNDLE_KEY_LOADER = "BUNDLE_KEY_LOADER";
    /**
     * Do we need to call {@link #doStart()} from the {@link #onLoadFinished(Loader, BasePresenter)} method.
     * Will be true if presenter wasn't loaded when {@link #onStart()} is reached
     */
    private final AtomicBoolean needToCallStart = new AtomicBoolean(false);
    /**
     * The presenter for this view
     */
    @Nullable
    protected P presenter;
    /**
     * Is this the first start of the activity (after onCreate)
     */
    private boolean firstStart;
    /**
     * Unique identifier for the loader, persisted across re-creation
     */
    private int loaderID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firstStart = savedInstanceState == null || savedInstanceState.getBoolean(BUNDLE_KEY_FIRST_START);
        loaderID = savedInstanceState == null ? BaseActivity.sViewCounter.incrementAndGet() : savedInstanceState.getInt(BUNDLE_KEY_LOADER);

        injectDependencies();

        getSupportLoaderManager().initLoader(loaderID, null, this).startLoading();
    }

    private void injectDependencies() {
        setupComponent(((App) getApplication()).getAppComponent());
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (presenter == null) {
            needToCallStart.set(true);
        } else {
            doStart();
        }
    }

    /**
     * Call the presenter callbacks for onStart
     */
    @SuppressWarnings("unchecked")
    private void doStart() {
        assert presenter != null;

        presenter.onViewAttached((V) this);

        presenter.onStart(firstStart);

        firstStart = false;
    }

    @Override
    protected void onStop() {
        if (presenter != null) {
            presenter.onStop();

            presenter.onViewDetached();
        }

        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(BUNDLE_KEY_FIRST_START, firstStart);
        outState.putInt(BUNDLE_KEY_LOADER, loaderID);
    }

    @Override
    public final Loader<P> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, getPresenterFactory());
    }

    @Override
    public final void onLoadFinished(Loader<P> loader, P presenter) {
        this.presenter = presenter;

        if (needToCallStart.compareAndSet(true, false)) {
            doStart();
        }
    }

    @Override
    public final void onLoaderReset(Loader<P> loader) {
        presenter = null;
    }

    /**
     * Get the presenter factory implementation for this view
     *
     * @return the presenter factory
     */
    @NonNull
    protected abstract PresenterFactory<P> getPresenterFactory();

    /**
     * Setup the injection component for this view
     *
     * @param appComponent the app component
     */
    protected abstract void setupComponent(@NonNull AppComponent appComponent);
}
