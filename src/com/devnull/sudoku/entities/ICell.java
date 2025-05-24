/**
 * 
 */
package com.devnull.sudoku.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Représentation logique d'une cellule que le joueur ou l'application doit
 * remplir pour arriver à une solution finale du jeu.<br>
 * 
 * @author Valère Bertin
 */
public interface ICell extends Serializable {
	/**
	 * Vide ou pas.
	 * @return true si la case est vide.
	 */
	public boolean isEmpty();
	
	/**
	 * Permet d'ajouter une autre valeur possible.
	 */
	public void addCandidateValue(int value);
	
	/**
	 * Permet de retirer une des autres valeurs possibles (lorsque la grille principale à
	 * été mise à jour)
	 */
	public void removeCandidateValue(int value);
	
	/**
	 * Pour savoir s'il y a des cellule de même valeur.
	 * @param value Valeur de de l'autre cellule à rechercher.
	 * @return true Si valeur trouvée.
	 */
	public boolean contains(int value);
	
	/** 
	 * Nombre de cellules.
	 */
	public int getCellCount();
	
	
	/**
	 * Converti en liste d'entiers.
	 * @return Liste des autres cellules.
	 */
	public List<Integer> cellsAsList();
}
