const express = require('express');
const PORT = process.env.PORT || 3050;
const app = express();
app.set('json spaces',2);
app.use(express.urlencoded({extended:false}));
app.use(express.json())
// Route
app.get('/', (req, res) => {
  res.send('Listo pa');
});
app.post('/figura_volumen', (req, res)=>{
  try{
      const pi = 3.1415
      const data= req.body
      v = 0
      if (data.id == 1){//cubo        
        v = raised(data.lado,3)
      } else if (data.id == 2){//piramide
          v = multiplicar(multiplicar(data.altura,1/3),raised(data.lado,2))
      } else if (data.id == 3){//cilindro
          v = multiplicar(multiplicar(pi,raised(data.radio,2)),data.altura)
      } else if (data.id == 4){//esfera
          v = multiplicar(multiplicar(4/3,pi),raised(data.radio,3))
      } else if (data.id == 5){//cono
          v = dividir(multiplicar(multiplicar(pi,raised(data.radio,2)),data.altura),3)
      } else if (data.id == 6){//ortoedro
          v = multiplicar(multiplicar(data.longitud,data.profundidad),data.altura)
      } else {
          v = 0
      }
      return res.status(200).json({"volumen":v})
  }catch(error){
      return res.status(500).json({"error":"vaya a ocurrido un error"});
  }
});
function add(x1,x2){
  return x1 + x2
}
function substraer(x1,x2){
  return x1 - x2
}
function multiplicar(x1,x2){
  return x1 * x2
}
function raised(x1,x2){
  x3 = x1
  i = 1
  while (i<x2){
      x1 = x1 * x3
      i=i+1
  }
  return x1
}
function dividir(x1,x2){
  if (x2 == 0) {
      return 0
  } else {
      return x1/x2
  }
}
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));