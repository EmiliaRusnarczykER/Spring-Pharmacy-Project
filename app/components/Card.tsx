import React from "react";
import { Card, CardHeader, CardBody, Image, CardFooter } from "@nextui-org/react";

export default function MedicineCard ({ data }: any) {

  return (
    <section className="grid grid-cols-4 justify-between">
      {
        data.map((medicine: any) => {
          return (
            <Card key={medicine.id} isFooterBlurred className="border-none m-4">

              <Image
                alt="Card background"
                className="object-cover rounded-xl"
                src={medicine.img}
                width={270}
              />
              <CardFooter className="justify-between flex-col  border-1 overflow-hidden py-1 absolute before:rounded-xl rounded-large bottom-1 w-[calc(100%_-_8px)] shadow-small ml-1 z-10">
                <h4 className="font-bold text-large">{medicine.title}</h4>
                <p className="text-cyan-400 font-extrabold text-tiny uppercase">{medicine.platform}</p>
                <small className="text-white">{medicine.price} €</small>
              </CardFooter>
            </Card>
          )
        })
      }
    </section>
  );
}