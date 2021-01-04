/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author omarq
 */
public class Problems {
    String Problem;

   
    int cost;

     public Problems() {
        cost=0;
        Problem="non";
    }

    public Problems(String Problem, int cost) {
        this.Problem = Problem;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return Problem+"........................"+cost;
    }
     
    public String getProblem() {
        return Problem;
    }

    public void setProblem(String Problem) {
        this.Problem = Problem;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
    
}
