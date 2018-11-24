/******************************************
CSC 250 Fall 2017 Project 3
Brian Rentsch
*******************************************/

public class BHTree {
    //Body or aggregate body stored within this node
    private Body body;
    
    //Square region that the tree represents
    private Quad quad;
    
    //Trees representing various quadrants
    private BHTree NW;
    private BHTree NE;
    private BHTree SW;
    private BHTree SE;
    
    //Threshold angle
    private final double THRESH_ANGLE = 2;
    
    public BHTree(Quad q) {
        this.body = null;
        this.quad = q;
        this.NW = null;
        this.NE = null;
        this.SW = null;
        this.SE = null;
    }
    
    //Check if a node of the tree is an external node 
    private boolean isExternal(BHTree t){
        if (t.NW == null && t.NE == null && t.SW == null && t.SE == null){
            return true;
        }
        else{
            return false;
        }
    }
    
    //Insert a body into the tree
    public void insert(Body b) {                
        if(this.body == null){                      //The current node is empty --> this node = given body
            this.body = b;
        }
        
        else if(!(this.isExternal(this))){          //Internal node (at least one child node != null)
            this.body = this.body.plus(b);          //update center of mass of current body
            
            if(b.in(this.quad.NW())){               //If given body is in the NW quadrant, insert it into the NW quadrant
                if(this.NW == null) {this.NW = new BHTree(this.quad.NW());}
                this.NW.insert(b);
            }
            else if(b.in(this.quad.NE())){          //If given body is in NE quad, insert it there  
                 if(this.NE == null) {this.NE = new BHTree(this.quad.NE());}
                 this.NE.insert(b);
            }   
            else if(b.in(this.quad.SW())){          //If given body is in SW quad, insert it there
                if(this.SW == null) {this.SW = new BHTree(this.quad.SW());}
                this.SW.insert(b);    
            }
            else if(b.in(this.quad.SE())){          //If given body is in SE quad, insert it there
                if(this.SE == null) {this.SE = new BHTree(this.quad.SE());}
                this.SE.insert(b);    
            }
        }
        
        else if(this.isExternal(this)){            //External node (all child nodes are null)
            this.NW = new BHTree(this.quad.NW());       //Create new BHTrees in all (empty) child nodes
            this.NE = new BHTree(this.quad.NE());
            this.SW = new BHTree(this.quad.SW());
            this.SE = new BHTree(this.quad.SE());
            
            if(this.body.in(this.quad.NW())){           //If the current body is in NW/NE/SW/SE quad, insert it there
                this.NW.insert(this.body);
            }
            else if(this.body.in(this.quad.NE())){
                this.NE.insert(this.body);
            }
            else if(this.body.in(this.quad.SW())){
                this.SW.insert(this.body);
            }
            else if(this.body.in(this.quad.SE())){
                this.SE.insert(this.body);
            }
            
            this.insert(b);                            //Insert given body into updated tree structure
            
            this.body = this.body.plus(b);             //Update center of mass of current body to represent masses of child bodies                 
        }
    }
    
    //Update forces within the BHTree
    public void updateForce(Body b) {
        if(this.isExternal(this) && this.body != b && this.body != null){
            b.addForce(this.body);          
        }
        
        else if (this.body == b || this.body == null){
            return;
        }
        
        else{
            if((this.quad.length() / this.body.distanceTo(b)) < THRESH_ANGLE){
                b.addForce(this.body);
            }
            else{
                this.NW.updateForce(b);
                this.NE.updateForce(b);
                this.SW.updateForce(b);
                this.SE.updateForce(b);
            }
        }
    }
    
    //Convert BHTree to string representation
    public String toString(){
        if(this.body == null){
            return new String("| Body: null | Quad: " + this.quad.toString() + " |");
        }
        else if(!(this.isExternal(this))){
            String strng1 = "";
            String strng2 = "";
            String strng3 = "";
            String strng4 = "";
            if(this.NW != null){strng1 = ("\t->NW" + this.NW.toString());}
            if(this.NE != null){strng2 = ("\t->NE" + this.NE.toString());}
            if(this.SW != null){strng3 = ("\t->SW" + this.SW.toString());}
            if(this.SE != null){strng4 = ("\t->SE" + this.SE.toString());}
            return new String("| Body: " + this.body.toString() + " | Quad: " + this.quad.toString() + " |\n" + strng1 + "\n" + strng2 + "\n" + strng3 + "\n" + strng4 + "\n");
        }
        else if(this.isExternal(this)){
            return new String("| Body: " + this.body.toString() + " | Quad: " + this.quad.toString());
        }
        return new String("oops");
    }
    
    //Draw the BHTree
    public void draw(){
        if(this.body != null){
            this.body.draw();
            this.NW.draw();
            this.NE.draw();
            this.SW.draw();
            this.SE.draw();
        }
    }
    
}