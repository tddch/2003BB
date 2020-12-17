package com.example.kaoshi.inesenter;

/**
 *
 */
public interface IModel<M> {
    void onModel(M m);
    void onError(String error);
}
