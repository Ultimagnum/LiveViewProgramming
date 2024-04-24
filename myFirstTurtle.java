Clerk.clear(); //Säubert den Bildschirm bei jedem Programmstart
Clerk.markdown("Oliver's Schildkröte");
Turtle turtle = new Turtle(200, 200);
int sides = 144
for (int i = 1; i <= sides; i++)
    turtle.right(10).forward(0.1*i);
