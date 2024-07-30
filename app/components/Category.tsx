'use client'
import { Card, CardHeader, CardBody, Image, CardFooter, Button } from "@nextui-org/react";

export default function CategoryCard ({ data }: any) {

  return (
    <section className="w-2/3 grid grid-cols-3 justify-between">
      {
        data.map((category: any) => {
          return (
            <Card key={category.id} className="border-none m-4">
              <a href={`/medicinegenres/${category.name}`}>
                <CardHeader className="pb-0 pt-2 px-4 flex-col items-start">
                  <h4 className="font-bold text-large">{category.name}</h4>
                </CardHeader>
                <CardBody className="overflow-visible py-2">
                  <p className="text-cyan-200 font-thin">{category.description}</p>
                </CardBody>
              </a>
            </Card>
          )
        })
      }
    </section>
  );
}