package com.siwoo.effectivejava.item5;

import java.util.Objects;
import java.util.function.Supplier;

public class Mosaic {
    private final Tile tile;

    private Mosaic(Tile tile) {
        this.tile = tile;
    }

    public static <T extends Tile> Mosaic of(Supplier<T> tileFactory) {
        Tile tile = Objects.requireNonNull(tileFactory.get());
        return new Mosaic(tile);
    }
}

interface Tile {

}

class WoodTile implements Tile {

}

class MetalTile implements Tile {

}

class ConcreteTile implements Tile {

}