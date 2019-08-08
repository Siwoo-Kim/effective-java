package com.siwoo.effectivejava.item5;

import javassist.compiler.Lex;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SpellChecker {
    private Lexicon dictionary;

    public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }

    public boolean isValid(String word) {
        return dictionary.hasWord(word);
    }

    public List<String> suggestions(String typo) {
        return dictionary.getSuggestions(typo);
    }

    public static void main(String[] args) {
        Lexicon mock = Mockito.mock(Lexicon.class);
        SpellChecker spellChecker = new SpellChecker(mock);
        spellChecker.isValid("hi");
    }

    //    private static final Lexicon dictionary = new Lexicon();
//
//    private SpellChecker() {
//        throw new AssertionError();
//    }
//
//    public static boolean isValid(String word) {
//        return dictionary.hasWord(word);
//    }
//
//    public static List<String> suggestions(String typo) {
//        return dictionary.getSuggestions(typo);
//    }
}

class Lexicon implements Set<String> {
    private static final Set<String> set;

    static {
        set = new HashSet<>(Arrays.asList("APPLE", "ORANGE", "GRAPE"));
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator<String> iterator() {
        return set.iterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }

    @Override
    public boolean add(String s) {
        return set.add(s);
    }

    @Override
    public boolean remove(Object o) {
        return set.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return set.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return set.removeAll(c);
    }

    @Override
    public void clear() {
        set.clear();
    }

    @Override
    public boolean equals(Object o) {
        return set.equals(o);
    }

    @Override
    public int hashCode() {
        return set.hashCode();
    }

    @Override
    public Spliterator<String> spliterator() {
        return set.spliterator();
    }

    @Override
    public boolean removeIf(Predicate<? super String> filter) {
        return set.removeIf(filter);
    }

    @Override
    public Stream<String> stream() {
        return set.stream();
    }

    @Override
    public Stream<String> parallelStream() {
        return set.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super String> action) {
        set.forEach(action);
    }

    public boolean hasWord(String word) {
        return contains(word);
    }

    public List<String> getSuggestions(String typo) {
        return contains(typo) ? Arrays.asList(typo): new ArrayList<>(set);
    }
}
