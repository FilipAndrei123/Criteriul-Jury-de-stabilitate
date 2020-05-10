import java.util.Scanner;


public class CriteriulJury {
    public static void main(String[] args) {
        int gradpol, i, j, m, n;
        System.out.println("Dati gradul polinomului");
        Scanner in = new Scanner(System.in);
        gradpol = in.nextInt();
        System.out.println("Dati coefientii polinomului");
        float[] v = new float[gradpol + 1];
        for (i = 0; i <= gradpol; i++) {
            System.out.println("Coeficientul de grad " + i);
            v[i] = in.nextFloat();
        }

        int ok = 1;

        float suma = 0;
        for (i = 0; i <= gradpol; i++) {
            suma = suma + v[i];
        }
        while (ok == 1)
        {

            if (suma <= 0) {
                System.out.println("Pc(1)=" + suma + " mai mic sau egal decat 0  si sistemul este instabil");
                ok = 0;
                break;
            }
            if (suma > 0) {
                System.out.println("Pc(1)=" + suma + " este mai mare ca zero si putem continua criteriul");
                ok=1;
            }

            float suma2 = v[0];
            if(gradpol%2==0) {
                for (i = 1; i <= gradpol; i++) {
                    suma2 = suma2 + (float) (v[i]*Math.pow(-1d,(double)i));
                }
            }

            if(gradpol%2==1) {
                for (i = 1; i <= gradpol; i++) {
                    suma2 = suma2 + (float) (v[i]*Math.pow(-1d,(double)i));
                }
                suma2=-suma2;
            }

            if (suma2 <= 0) {
                System.out.println("(-1)^n*Pc(-1)=" + suma2 + " mai mic sau egal decat 0  si sistemul este instabil");
                ok = 0;
                break;
            }
            if (suma2 > 0) {
                System.out.println("(-1)^n*Pc(-1)=" + suma2 + " este mai mare ca zero si putem continua criteriul");
                ok=1;
            }

            if(Math.abs(v[gradpol])>Math.abs(v[0]))
            {
                System.out.println(v[gradpol]+">"+v[0]+" la valoare absoluta si putem continua criteriul");
                ok=1;
            }
            if(Math.abs(v[gradpol])<=Math.abs(v[0]))
            {
                System.out.println(v[gradpol]+"<="+v[0]+" la valoare absoluta si sistemul este este instabil");
                ok = 0;
                break;
            }

            if(gradpol<=2&&ok==1)
            {
                System.out.println("Polinomul caracteristic indeplineste primele 3 conditii din criteriul lui Jury si sistemul este stabil");
                break;
            }

            if(gradpol>2&&ok==1)
            {
                System.out.println("Polinomul caracteristic indeplineste primele 3 conditii din criteriul lui Jury si putem continua");
                System.out.println();
            }


            System.out.println("Constructia matricii pe criteriul Jurry");
            System.out.println();

            m=gradpol+2;
            n=3+(gradpol-3)*2;
            float[][] matriceJury = new float[n][m];
            for( i = 0; i < n; i++)
                for(j = 0; j < m; j++)
                    matriceJury[i][j] = 0;


            for(j=0;j<=gradpol;j++)
                matriceJury[0][j]=v[gradpol-j];
            for(j=0;j<=gradpol;j++)
                matriceJury[1][j]=v[j];

            int z=m;
            for(i=2;i<n;i++)
            {
                if(i%2==0)
                    for (j = 0; j < z-1; j++)
                        matriceJury[i][j] = matriceJury[i - 2][0] * matriceJury[i - 1][z - j - 2] - matriceJury[i - 1][0] * matriceJury[i - 2][z - j - 2];
                if(i%2==1) {
                    for (j = 0; j < z - 2; j++)
                        matriceJury[i][j] = matriceJury[i - 1][z - j - 3];
                    z = z - 1;
                }
            }

            for (i = 0; i < n; i++) {
                for (j = 0; j < m-1; j++) {
                    System.out.printf("%.2f", matriceJury[i][j]);
                    System.out.print(" | ");
                }
                System.out.println();
            }
            System.out.println();

            System.out.println("Dupa constructia matricii trebuie sa incepem sa comparam termenul corespunzator gradului maxim cu termenul corespunzator gradului 0 la valori absolute");

            int[] verificare = new int[(n-1)/2+1];
            for (i = 0; i < n-1; i=i+2)
            {
                System.out.print("Comparam valaorea absoluta al lui  "+matriceJury[i][0] +" cu valoarea absoluta al lui "+ matriceJury[i+1][0]);
                if(Math.abs(matriceJury[i][0])>Math.abs(matriceJury[i+1][0])) {
                    verificare[i / 2] = 1;
                    System.out.println(" si se observa ca este mai mare");
                }
                if(Math.abs(matriceJury[i][0])<=Math.abs(matriceJury[i+1][0])) {
                    verificare[i / 2] = 0;
                    System.out.println(" si se observa ca este mai mic");
                }

            }
            System.out.print("Comparam valaorea absoluta al lui  "+matriceJury[n-1][0] +" cu valoarea absoluta al lui "+matriceJury[n-1][2]);
            if(Math.abs(matriceJury[n-1][0])>Math.abs(matriceJury[n-1][2])) {
                verificare[(n - 1) / 2] = 1;
                System.out.println(" si se observa ca este mai mare");
            }
            if(Math.abs(matriceJury[n-1][0])<=Math.abs(matriceJury[n-1][2])) {
                verificare[(n - 1) / 2] = 0;
                System.out.println(" si se observa ca este mai mic");
            }

            int sumaverificare=0;
            for(i=0;i<(n-1)/2+1;i++)
                sumaverificare=sumaverificare+verificare[i];

            System.out.println();
            if(sumaverificare==((n-1)/2+1))
            {
                System.out.println("Sistemul este stabil si am terminat criteriul Jury");
                break;
            }
            else
            {
                System.out.println("Am terminat criteriul Jury dar sistemul este instabil");
                break;
            }
        }
    }

}
