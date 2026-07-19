package fr.vborg.sudoku.sudoku.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import fr.vborg.sudoku.game.Move;
import fr.vborg.sudoku.localization.Messages;

class MoveTest
{
    @AfterEach
    void restoreLocale()
    {
        Messages.setLocale(Locale.FRENCH);
    }


    @Test
    void shouldFormatMoveInFrench()
    {
        Messages.setLocale(Locale.FRENCH);

        Move move = new Move(0, 1, 3);

        assertEquals(
                "Coup[ligne=1, colonne=2, valeur=3]",
                move.format());
    }


    @Test
    void shouldFormatMoveInEnglish()
    {
        Messages.setLocale(Locale.ENGLISH);

        Move move = new Move(0, 1, 3);

        assertEquals(
                "Move[row=1, column=2, value=3]",
                move.format());
    }


    @Test
    void shouldBeEqualWhenValuesAreIdentical()
    {
        Move first =
                new Move(1, 2, 3);

        Move second =
                new Move(1, 2, 3);

        assertEquals(
                first,
                second);
    }
}
