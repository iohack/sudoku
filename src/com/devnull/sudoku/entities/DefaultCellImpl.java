/**
 * 
 */
package com.devnull.sudoku.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Représentation logique constituée de sa valeur, le fait d'être vide 
 * ou pas et la liste des valeurs de cellules éligibles.
 * TODO reporter doc
 */
public class DefaultCellImpl implements Serializable, ICell {
	public static final Logger logger.
	/**
	 * ID.
	 */
	private static final long serialVersionUID = -1886574328413537104L;

	/**
	 * La cellule est vide ou remplie ?
	 */
	private boolean empty;
	
	/**
	 * La valeur de la cellule.
	 */
	private Integer value;
	
	/**
	 * Valeurs éligibles.
	 */
	private final BitSet candidateCells;
	
	/**
	 * Le nombre maximum de cellules éligibles.
	 */
	private final int maxCandidateCells;
	
	
	/**
	 * Constructeur unique.
	 * @param pMaxCandidates Nombre macimum de valeurs.
	 */
	public DefaultCellImpl(final int pMaxCandidates) {
		// Initialisation des attributs.
		empty = true;		
		value=0;
		maxCandidateCells = pMaxCandidates;
		candidateCells = new BitSet(maxCandidateCells);
		
		// Activation de tous les bits.
		candidateCells.set(0,maxCandidateCells);
	}
	
	
	
	@Override
	public boolean isEmpty() {
		return empty;
	}

	@Override
	public void addCandidateValue(int value) {
		candidateCells.set(value - 1);
	}

	@Override
	public void removeCandidateValue(int value) {
		 candidateCells.clear(value - 1);
	}

	@Override
	public boolean contains(int value) {
		return candidateCells.get(value - 1);
	}

	@Override
	public int getCellCount() {
		return candidateCells.cardinality();
	}

	@Override
	public List<Integer> cellsAsList() {
		List<Integer> list = new ArrayList<>(64);
        for (int i = candidateCells.nextSetBit(0); i >= 0; i = candidateCells.nextSetBit(i + 1)) {
            list.add(i + 1);
        }
        return list;
	}
	
	// TODO DEBUG
	private String cellToString() {
		List<Integer> list = cellsAsList();
		StringBuilder sb=new StringBuilder("[empty="+empty+", value="+value+", maxCandidateCells=" + maxCandidateCells+ ", cells=");
		for(Integer i : list) {
			sb.append(i+",]");
		}
		return sb.toString();
	}
	
	// TODO DEBUG
	public static void main(String [] args) {
		DefaultCellImpl cell = new DefaultCellImpl(9);
		System.out.println(cell.cellToString());
	}
}
