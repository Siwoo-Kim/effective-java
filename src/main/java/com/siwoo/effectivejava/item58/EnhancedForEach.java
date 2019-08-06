package com.siwoo.effectivejava.item58;

import java.util.*;

public class EnhancedForEach {

    enum Suit { CLUB, DIAMOND, HEART, SPADE }
    enum Rank { ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING }
    enum Face { ONE, TWO, THREE, FOUR, FIVE, SIX }
    static Collection<Suit> suits = Arrays.asList(Suit.values());
    static Collection<Rank> ranks = Arrays.asList(Rank.values());
    static Collection<Face> faces = Arrays.asList(Face.values());

    public static void main(String[] args) {
        for (Iterator<Face> i=faces.iterator(); i.hasNext(); )
            for (Iterator<Face> j=faces.iterator(); i.hasNext(); )
                System.out.println(i.next() + " " + j.next());

        List<Card> deck = new ArrayList<>();
        for (Iterator<Suit> i=suits.iterator(); i.hasNext(); ) {
            Suit suit = i.next();
            for (Iterator<Rank> j=ranks.iterator(); j.hasNext(); )
                deck.add(Card.of(suit, j.next()));
        }

        deck = new ArrayList<>();
        for (Suit suit: suits)
            for (Rank rank: ranks)
                deck.add(Card.of(suit, rank));

        System.out.println(deck);


    }

    static class Card {
        private final Suit suit;
        private final Rank rank;

        public Card(Suit suit, Rank rank) {
            this.suit = suit;
            this.rank = rank;
        }

        public static Card of(Suit suit, Rank rank) {
            return new Card(Objects.requireNonNull(suit),
                    Objects.requireNonNull(rank));
        }

        @Override
        public String toString() {
            return "Card{" +
                    "suit=" + suit +
                    ", rank=" + rank +
                    '}';
        }
    }
}
