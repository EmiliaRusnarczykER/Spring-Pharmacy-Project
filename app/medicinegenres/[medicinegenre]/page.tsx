'use client'

import MedicineCard from "@/app/components/Card";
import CategoryCard from "@/app/components/Category";
import { useEffect, useState } from "react";

interface PageProps {
  params: {
    medicinegenre: string;
  };
}

const Page = ({ params }: PageProps) => {
  const { medicinegenre } = params;
  const [medicines, setMedicines] = useState<any[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await fetch(`http://localhost:8080/api/typemedicines/name/${medicinegenre}`, {
          method: 'GET',
          cache: 'force-cache'
        });

        if (!res.ok) {
          throw new Error('Failed to fetch data');
        }

        const data = await res.json();
        const genre = data[0];
        const medicinesData = genre.medicines || [];
        setMedicines(medicinesData);
      } catch (error) {
        console.error('Error fetching data:', error);
        // Handle error state or logging as needed
      }
    };

    fetchData();
  }, [medicinegenre]);

  return (
    <main className="flex max-h-screen flex-col items-center justify-between p-24">
      <CategoryCard data={medicines} />
      <MedicineCard data={medicines} />
    </main>
  );
};

export default Page;
