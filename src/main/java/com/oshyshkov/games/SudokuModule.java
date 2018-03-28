package com.oshyshkov.games;

import com.google.inject.AbstractModule;

public class SudokuModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Generator.class).to(RandomGenerator.class);
    }
}
