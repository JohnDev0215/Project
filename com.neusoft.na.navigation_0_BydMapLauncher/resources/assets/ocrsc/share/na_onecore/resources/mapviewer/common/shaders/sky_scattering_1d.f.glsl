
#ifdef GL_ES
    precision mediump float;
#endif

varying vec3 vNrm;

uniform sampler2D scatteringTable;

uniform vec3 uSunLightDirection;

const float PI = 3.141592654;

// Rayleigh scale height
const float Hr = 7.994;

// Mie scale height
const float Hm = 1.2;
// Earth radius
const float radiusEarth = 6360.0;
// Atmosphere radius
const float radiusAtmosphere = 6519.0;

const float radiusDelta = radiusAtmosphere - radiusEarth;


#define numSamples 10


float intersect(in vec3 start, in vec3 dir)
{
   float a = dot(dir, dir);
   float b = 2.0 * dot(dir, start);
   float c = dot(start, start) - radiusAtmosphere * radiusAtmosphere;

   float d = b * b - 4.0 * a * c;

   float t = (-b + sqrt(d)) / (2.0 * a);
   
   return t;
}

float getRayleigh(float height, in vec3 dir)
{
   vec3 tmp = 255.0 * texture2D(scatteringTable, vec2( acos(dir.z) / PI , 0.25)).rgb;
   float od0 = tmp.r * 65536.0 + tmp.g * 256.0 + tmp.b;
   float r = exp(-4.0 * height) * od0;
   return r;
}

float getMie(float height, in vec3 dir)
{
   vec3 tmp = 255.0 * texture2D(scatteringTable, vec2( acos(dir.z) / PI , 0.75)).rgb;
   float od0 = tmp.r * 65536.0 + tmp.g * 256.0 + tmp.b;
   float m = exp(-4.0 * height) * od0;
   return m;
}

vec3 tonemapUncharted(in vec3 x)
{
   const float A = 0.15;
   const float B = 0.50;
   const float C = 0.10;
   const float D = 0.20;
   const float E = 0.02;
   const float F = 0.30;
   const float W = 11.2;
   return ((x*(A*x+C*B)+D*E)/(x*(A*x+B)+D*F))-E/F;

}

void main(void)
{
   vec3 viewDir = normalize(vNrm);
   
   vec3 betaR = vec3(5.5e-6, 13.0e-6, 22.4e-6);
   vec3 betaM = vec3(21e-6, 21e-6, 21e-6);

   vec3 start = vec3(0.0, 0.0, radiusEarth);

   float t = intersect(start, viewDir);

   float segmentLength = t / float(numSamples);
   float tCurrent = 0.0;
   vec3 sumR = vec3(0.0);
   vec3 sumM = vec3(0.0);
   float opticalDepthR = 0.0;
   float opticalDepthM = 0.0;

   float mu = dot(viewDir, uSunLightDirection);
   float phaseR = 3.0 / (16.0 * PI) * (1.0 + mu * mu);
   const float g = 0.98;
   float phaseM = 3.0 / (8.0 * PI) * ((1.0 - g * g) * (1.0 + mu * mu))/((2.0 + g * g) * pow(1.0 + g * g - 2.0 * g * mu, 1.5));

   vec3 stepDelta = viewDir * segmentLength;
   vec3 samplePosition = start + 0.5 * stepDelta;
   for(int i = 0; i < numSamples; i++)
   {
      float height = length(samplePosition) - radiusEarth;

      float hr = exp(-height / Hr) * segmentLength * 1000.0;
      float hm = exp(-height / Hm) * segmentLength * 1000.0;
      opticalDepthR += hr;
      opticalDepthM += hm;

      // light optical depth
      float h = clamp(height / radiusDelta, 0.0, 1.0);
      
      float opticalDepthLightR = getRayleigh(h, uSunLightDirection);
      float opticalDepthLightM = getMie(h, uSunLightDirection);

      if(opticalDepthLightR > 0.0 && opticalDepthLightM > 0.0)
      {
         vec3 tau = betaR * (opticalDepthR + opticalDepthLightR) + betaM * 1.1 * (opticalDepthM + opticalDepthLightM);

         vec3 attenuation = exp(-tau);
         sumR += attenuation * hr;
         sumM += attenuation * hm;
      }
      samplePosition += segmentLength;
   }

   vec3 sr = vec3(2.5);
   vec3 col = 20.0 * (sumR * phaseR * betaR * sr + sumM * phaseM * betaM);

   if(true)
   {
      col /= (col + vec3(1.0));
      col = pow(col, vec3(1.0 / 2.2));
   }
   else
   {
      float ExposureBias = 2.0;
      vec3 curr = tonemapUncharted(ExposureBias * col);
      vec3 whiteScale = 1.0 / tonemapUncharted(vec3(11.2));
      vec3 color = curr * whiteScale;
      col = pow(color, vec3(1.0 / 2.2));
   }

   gl_FragColor = vec4(col, 1.0);
}
// file:sky_scattering_1d.f.glsl
