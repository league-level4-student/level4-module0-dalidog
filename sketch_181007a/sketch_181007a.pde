  class Brick{
  Brick(int x, int y, int width, int height){
  rect(x,y,width,height);
  }}
  int number=14;
  int x;
  int y;
  int row =0;
void setup(){
  width=30;
  height=12;
  x=40;
  y=500;
  size(500,500);
 
}
void draw(){ 
   for(int i=0;i<number;i++){
      Brick b = new Brick(x,y,width,height);
      x=x+30;
      row=row+1;
    }
    number=number-1;
    y=y-12;
    x=40-(15*row);
}

  