package threeeighthsintegrationapp;


public class ThreeEighthsIntegrationApp {


    public static void main(String[] args) {
        int sizeOfLineSegments = 66;
        int sizeOfPoints = sizeOfLineSegments + 1;
        
        double a = 0.0;
        double b = 4.0;
        
        double h = (b - a) / sizeOfLineSegments;
        
        double[] x = new double[sizeOfPoints];
        x[0] = a;
        x[sizeOfPoints - 1] = b;
        for(int i = 1; i < sizeOfPoints - 1; i++)
            x[i] = x[0] + i*h;
        
        double[] y = new double[sizeOfPoints];
        for(int i = 0; i < sizeOfPoints; i++)
            y[i] = 1 / (1 + Math.sqrt(x[i]));    
              
        double integral = y[0] + y[sizeOfPoints - 1];
        for(int i = 1; i < sizeOfPoints - 1; i++)
            if(i % 3 == 0)
                integral += 2*y[i];
            else
                integral += 3*y[i];
        integral *= 3*h/8;
        
        double[] der = new double[sizeOfPoints];
        for(int i = 0; i < sizeOfPoints; i++){
            der[i] = -3 * (Math.sqrt(x[i]) * (Math.pow(x[i], -1) + 3) + 4) / 2;
            der[i] -= (3 * Math.sqrt(x[i]) + 1)  *   (1 * (Math.pow(x[i], -1) + 3)) / 2 - Math.pow(x[i], -1);
            der[i] += (Math.sqrt(x[i]) * (1.5 - Math.pow(x[i], -1) / 2) + (Math.sqrt(x[i]) + 1) * (1.5 + Math.pow(x[i], -1) / 2)) / 2;
            der[i] -= 8 * (Math.sqrt(x[i]) + 1) + 10 * x[i]; 
            der[i] *= x[i] * (Math.sqrt(x[i]) + 1) * Math.pow(x[i], -1) * (1.5 - Math.pow(x[i], -1) / 2);
            der[i] *= -(3 * Math.sqrt(x[i])) * (Math.sqrt(x[i]) * (Math.pow(x[i], -1) + 3) + 4);
            der[i] *= 4 * Math.sqrt(x[i]) * (Math.sqrt(x[i]) + 1);
            der[i] *= x[i] / (16 * Math.pow(x[i], 4) * Math.pow(Math.sqrt(x[i]) + 1, 6));                 
        }
        
        double M4 = 0;
        for(int i = 1; i < sizeOfPoints; i++)
            if(Math.abs(der[i]) > M4)
                M4 = Math.abs(der[i]);
        
        double R = -(b - a) / 80;
        R *= M4 * Math.pow(h, 4);
        
        /*System.out.println("x[i]:");
        for(int i = 0; i < sizeOfPoints; i++)
            System.out.println(x[i]);
        System.out.println();
        
        System.out.println("y[i]:");
        for(int i = 0; i < sizeOfPoints; i++)
            System.out.println(y[i]);
        System.out.println();
        
        System.out.println("Result of integration without R(h):");
        System.out.println(integral);
        System.out.println();
        
        
        System.out.println("R(h):");
        System.out.println(R);
        System.out.println();
        
        System.out.println("Result of integration with R(h):");
        System.out.println(integral + R);*/
        
        System.out.println("Function: 1/(1+sqrt(x))");
        System.out.println("[" + a + "]" + "[" + b +"]");
        System.out.println("n = " + sizeOfLineSegments);
        System.out.println("Three of Eight's method");
        System.out.println("****************");
        System.out.println("Answer = " + integral);
        System.out.println("R = " + R);
        System.out.println("Answer with R = " + (integral + R));       
        
    }
    
}
