import { MedicineCardEdit } from "./MedicineCardEdit";
import { InputCreate } from "./InputCreate";

interface MedicineProps {
  medicine: {
    id: number;
    title: string;
    platform: string;
    price: number;
    img: string;
  };
}

const MedicineCardList = ({ data }: any) => {
  return (
    <section className="grid grid-cols-4 justify-between">
      <InputCreate />
      {
        data.map((medicine: any) => (
          <MedicineCardEdit medicine={medicine} key={medicine.id} />
        ))
      }
    </section>
  );
}

export default MedicineCardList;
