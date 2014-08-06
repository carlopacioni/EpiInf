/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package epiinf;

import beast.evolution.tree.Node;

/**
 * Class representing events on a transmission tree.
 *
 * @author Tim Vaughan <tgvaughan@gmail.com>
 */
public class TreeEvent {
    public enum Type { COALESCENCE, LEAF };
    
    public Type type;
    public int multiplicity = 1;
    public double time;
}
