public class NBody {
    public static void main(String[] args) {

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        StdOut.println("T = " + String.format("%.5e", T));
        StdOut.println("dt = " + dt);

        double t = 0;

        int n = StdIn.readInt();
        double radius = StdIn.readDouble();

        // Creates arrays
        double[] px = new double[n];
        double[] py = new double[n];
        double[] vx = new double[n];
        double[] vy = new double[n];
        double[] mass = new double[n];
        String[] image = new String[n];


        String background = "starfield.jpg";

        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);

        StdDraw.enableDoubleBuffering();

        StdAudio.play("2001.wav");

        // Reads Information
        for (int i = 0; i < n; i++) {
            px[i] = StdIn.readDouble();
            py[i] = StdIn.readDouble();
            vx[i] = StdIn.readDouble();
            vy[i] = StdIn.readDouble();
            mass[i] = StdIn.readDouble();
            image[i] = StdIn.readString();
        }

        StdOut.println(n);
        StdOut.println(String.format("%.2e", radius));

        // Prints data values
        for (int i = 0; i < n; i++) {
            StdOut.print(String.format("%11.4e", px[i]) + "  ");
            StdOut.print(String.format("%11.4e", py[i]) + "  ");
            StdOut.print(String.format("%11.4e", vx[i]) + "  ");
            StdOut.print(String.format("%11.4e", vy[i]) + "  ");
            StdOut.print(String.format("%11.4e", mass[i]) + "  ");
            StdOut.println(image[i] + "  ");
        }

        // Makes arrays and variables
        double[] dx = new double[n];
        double[] dy = new double[n];
        double r = 0;
        double force = 0;
        double[] fx = new double[n];
        double[] fy = new double[n];
        double[] ax = new double[n];
        double[] ay = new double[n];


        // Draws new frame
        while (t < T) {

            // Resets fx and fy values to 0
            for (int i = 0; i < n; i++) {
                fx[i] = 0;
                fy[i] = 0;
            }

            StdDraw.picture(0, 0, background);

            // Finds force
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {

                        dx[i] = (px[j] - px[i]);
                        dy[i] = (py[j] - py[i]);

                        r = Math.sqrt((dx[i] * dx[i]) + (dy[i] * dy[i]));

                        force = (6.67e-11 * mass[i] * mass[j]) / (r * r);

                        fx[i] = fx[i] + (force * (dx[i] / r));
                        fy[i] = fy[i] + (force * (dy[i] / r));
                    }
                }
            }

            // Finds acceleration and changes velocity and position values
            for (int i = 0; i < n; i++) {

                ax[i] = fx[i] / mass[i];
                ay[i] = fy[i] / mass[i];


                vx[i] = vx[i] + (ax[i] * dt);
                vy[i] = vy[i] + (ay[i] * dt);

                px[i] = px[i] + (vx[i] * dt);
                py[i] = py[i] + (vy[i] * dt);
            }

            // Draws each image
            for (int i = 0; i < n; i++) {
                StdDraw.picture(px[i], py[i], image[i]);
            }

            StdDraw.show();
            // StdDraw.pause(20);

            t = t + dt;
        }

        // Reprints data values
        for (int i = 0; i < n; i++) {
            StdOut.print(String.format("%11.4e", px[i]) + "  ");
            StdOut.print(String.format("%11.4e", py[i]) + "  ");
            StdOut.print(String.format("%11.4e", vx[i]) + "  ");
            StdOut.print(String.format("%11.4e", vy[i]) + "  ");
            StdOut.print(String.format("%11.4e", mass[i]) + "  ");
            StdOut.println(image[i] + "  ");
        }

    }
}
